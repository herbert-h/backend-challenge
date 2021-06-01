package user

import (
	"fmt"

	log "github.com/sirupsen/logrus"

	"github.com/herbert/sample-rule-engine/internal/business/user"
	db_util "github.com/herbert/sample-rule-engine/internal/database/util"
	"github.com/jmoiron/sqlx"
)

type RepositoryImp struct {
	db *sqlx.DB
}

func NewRepository(db *sqlx.DB) *RepositoryImp {
	return &RepositoryImp{db: db}
}

const (
	table_name = "users"
)

func (r *RepositoryImp) GetById(userId string) (*user.User, error) {
	query := db_util.QueryBuilder{Value: fmt.Sprintf("SELECT id, first_name, last_name, date_of_birth::date, created_at FROM %s WHERE id = ':user_id';", table_name)}
	query = query.Bind("user_id", userId)

	result, err := r.db.Query(query.Value)
	if err != nil {
		return nil, err
	}
	defer result.Close()

	var user user.User
	result.Next()
	if err := result.Scan(&user.Id, &user.FirstName, &user.LastName, &user.DateOfBirth, &user.CreatedAt); err != nil {
		return nil, err
	}
	log.Infof("Recover User: %v", user)
	return &user, nil
}
