package config

import (
	"strings"

	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
)

func ConfigureEcho() *echo.Echo {
	e := echo.New()
	e.Use(middleware.LoggerWithConfig(loggerConfig()))

	return e
}

func loggerConfig() middleware.LoggerConfig {
	return middleware.LoggerConfig{
		Skipper: loggerSkipperConfig(),
	}
}

func loggerSkipperConfig() func(c echo.Context) bool {
	return func(c echo.Context) bool {
		return strings.Contains(c.Request().URL.Path, "/health")
	}
}
