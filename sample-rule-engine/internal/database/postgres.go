package database

import (
	"time"

	"github.com/jmoiron/sqlx"
	"github.com/spf13/viper"
)

const (
	postgres = "postgres"
)

func New(sqlConnection string) (*sqlx.DB, error) {
	db, err := sqlx.Connect(postgres, sqlConnection)
	if err != nil {
		return nil, err
	}

	maxIdleConns := viper.GetInt("max-idle-conns")
	maxOpenConns := viper.GetInt("max-open-conns")
	connMaxLifetime := viper.GetDuration("conn-max-lifetime")

	db.SetMaxIdleConns(maxIdleConns)
	db.SetMaxOpenConns(maxOpenConns)
	db.SetConnMaxLifetime(connMaxLifetime * time.Millisecond)

	return db, nil
}
