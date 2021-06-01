package config

import (
	"os"

	log "github.com/sirupsen/logrus"
)

func ConfigureLogger() {
	log.SetFormatter(&log.TextFormatter{
		ForceColors:   true,
		DisableColors: false,
		FullTimestamp: true,
	})

	log.SetOutput(os.Stdout)
}
