package health

import (
	"net/http"

	"github.com/labstack/echo"
)

type Router struct {
	e   *echo.Echo
	svc Service
}

func NewRouter(e *echo.Echo) *Router {
	return &Router{
		e:   e,
		svc: NewService(),
	}
}

func (r *Router) Routes() {
	r.e.GET("/health", r.getStatus)
}

func (r *Router) getStatus(c echo.Context) error {
	healthCheck := r.svc.GetStatus()
	return c.JSON(http.StatusOK, healthCheck)
}
