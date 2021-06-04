package user

import "github.com/herbert/sample-rule-engine/internal/business/user/model"

type Repository interface {
	GetById(userId string) (*model.User, error)
}
