package application

import (
	"net"
	"net/http"

	"github.com/jmoiron/sqlx"
	log "github.com/sirupsen/logrus"

	"github.com/herbert/sample-rule-engine/cmd/application/config"
	"github.com/herbert/sample-rule-engine/internal/business/discount"
	"github.com/herbert/sample-rule-engine/internal/business/health"
	"github.com/labstack/echo"
	"github.com/spf13/viper"
	"google.golang.org/grpc"
)

type App struct {
	*http.Server
	e *echo.Echo
	s *grpc.Server

	// Database Connection
	db *sqlx.DB

	// RoutersHttp
	healthRouter *health.Router

	// ServersGrpc
	discountServer *discount.GrpcServer
}

func NewApp() *App {
	config.ConfigureLogger()
	config.ConfigureEnv()
	e := config.ConfigureEcho()
	s := config.ConfigureGrpc()
	db := config.ConfigDatabase()

	app := &App{
		e:  e,
		s:  s,
		db: db,

		healthRouter: health.NewRouter(e),

		discountServer: discount.NewGrpcServer(s, db),
	}

	app.initHttpRoutes()
	app.initGrpcServers()

	return app
}

func (a *App) StartHttp() {
	port := viper.GetString("application.http.port")
	a.e.Logger.Fatal(a.e.Start(port))
}

func (a *App) StartGrpc() {
	port := viper.GetString("application.grpc.port")
	log.Infof("Starting grpc server at port: %v", port)
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	if err := a.s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}

func (a *App) initHttpRoutes() {
	a.healthRouter.Routes()
}

func (a *App) initGrpcServers() {
	a.discountServer.RegisterServer()
}
