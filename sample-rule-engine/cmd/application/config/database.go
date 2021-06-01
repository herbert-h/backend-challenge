package config

import (
	"fmt"
	"os"

	db "github.com/herbert/sample-rule-engine/internal/database"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq"
	log "github.com/sirupsen/logrus"
	"github.com/spf13/viper"
)

func ConfigDatabase() *sqlx.DB {
	host := viper.GetString("database.url")
	port := viper.GetInt("database.port")
	username := viper.GetString("database.username")
	password := viper.GetString("database.password")
	dbName := viper.GetString("database.database")

	conn := createConn(password, username, host, port, dbName)

	return conn
}

func createConn(password string, username string, host string, port int, dbName string) *sqlx.DB {
	if password == "" {
		password = os.Getenv("DB_PASS")
	}

	if host == "" {
		host = os.Getenv("DB_HOST")
	}

	dbURL := fmt.Sprintf("postgres://%s:%s@%s:%d/%s?sslmode=disable", username, password, host, port, dbName)

	conn, err := db.New(dbURL)
	if err != nil {
		log.Fatalf("A fatal error occurred while trying to connect to the database: %+v", err)
	}
	return conn
}
