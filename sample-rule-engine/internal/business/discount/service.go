package discount

import (
	"github.com/google/uuid"
	"github.com/herbert/sample-rule-engine/internal/business/discount/model"
	"github.com/herbert/sample-rule-engine/internal/business/product"
	"github.com/herbert/sample-rule-engine/internal/business/rule"
	user "github.com/herbert/sample-rule-engine/internal/business/user"
	user_db "github.com/herbert/sample-rule-engine/internal/database/user"
	"github.com/jmoiron/sqlx"
	log "github.com/sirupsen/logrus"
)

type Service interface {
	Apply(productId string, userId string) (float32, error)
}

type ServiceImp struct {
	UserRepository user.Repository
	RuleService    rule.Service
}

func NewService(db *sqlx.DB) *ServiceImp {
	return &ServiceImp{
		UserRepository: user_db.NewRepository(db),
		RuleService:    rule.NewService(),
	}
}

func (svc ServiceImp) Apply(productId string, userId string) (float32, error) {

	// I think have better way to do somethig like this in GO
	user := getUser(&svc, userId)

	// I not create other class to get Product from db because not have rule for product at this time
	// Just for save time in this challenge xD
	product := &product.Product{Id: productId}

	// How i dont have product here, I just will calculate percentage and not entire discount class
	// Saving time again
	discount := &model.Discount{Percentage: 0.0}

	chain := svc.RuleService.BuildRuleChain()
	chain.Execute(user, product, discount)

	return discount.Percentage, nil
}

func getUser(s *ServiceImp, userId string) *user.User {
	if isValidUUID(userId) {
		user, err := s.UserRepository.GetById(userId)
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
