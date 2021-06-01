package config

import (
	"flag"
	"os"

	log "github.com/sirupsen/logrus"
	"github.com/spf13/viper"
)

const (
	configFilePath = "./configs"
	envDefault     = "local"
)

func ConfigureEnv() {
	var env string
	envByEnvironment := os.Getenv("ACTIVE_PROFILE")

	if envByEnvironment != "" {
		env = envByEnvironment
	} else {
		env = envDefault
	}

	if flag.Lookup("test.v") != nil {
		env = "test"
	}

	log.Infof("Environment is: %s", env)

	viper.SetConfigName("application_" + env)
	viper.AddConfigPath(configFilePath)

	err := viper.ReadInConfig()
	if err != nil {
		log.Fatalf("fatal: %+v", err)
	}
}
