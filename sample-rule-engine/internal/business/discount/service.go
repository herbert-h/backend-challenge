package discount

import (
	"github.com/herbert/sample-rule-engine/internal/business/discount/model"
	"github.com/herbert/sample-rule-engine/internal/business/product"
	"github.com/herbert/sample-rule-engine/internal/business/rule"
	user "github.com/herbert/sample-rule-engine/internal/business/user"
	user_db "github.com/herbert/sample-rule-engine/internal/database/user"
	"github.com/jmoiron/sqlx"
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
	user, err := svc.UserRepository.GetById(userId)
	if err != nil {
		return 0, err
	}

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
