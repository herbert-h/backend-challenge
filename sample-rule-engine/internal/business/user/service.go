package user

import (
	"github.com/google/uuid"
	"github.com/herbert/sample-rule-engine/internal/business/user/model"
	user_db "github.com/herbert/sample-rule-engine/internal/database/user"
	"github.com/jmoiron/sqlx"
	log "github.com/sirupsen/logrus"
)

type Service interface {
	GetUserById(userId string) *model.User
}

type ServiceImp struct {
	UserRepository Repository
}

func NewService(db *sqlx.DB) *ServiceImp {
	return &ServiceImp{
		UserRepository: user_db.NewRepository(db),
	}
}

func (svc ServiceImp) GetUserById(userId string) *model.User {
	if isValidUUID(userId) {
		user, err := svc.UserRepository.GetById(userId)
		if err != nil {
			log.Warnf("Could not get user from database: err=%v", err)
			return nil
		}
		return user
	}
	return nil
}

func isValidUUID(id string) bool {
	_, err := uuid.Parse(id)
	return err == nil
}
