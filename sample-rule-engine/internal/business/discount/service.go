package discount

import (
	disc "github.com/herbert/sample-rule-engine/internal/business/discount/model"
	prod "github.com/herbert/sample-rule-engine/internal/business/product/model"
	"github.com/herbert/sample-rule-engine/internal/business/rule"
	"github.com/herbert/sample-rule-engine/internal/business/user"
	"github.com/jmoiron/sqlx"
)

type Service interface {
	Apply(productId string, userId string) (float32, error)
}

type ServiceImp struct {
	UserService user.Service
	RuleService rule.Service
}

func NewService(db *sqlx.DB) *ServiceImp {
	return &ServiceImp{
		UserService: user.NewService(db),
		RuleService: rule.NewService(),
	}
}

func (svc ServiceImp) Apply(productId string, userId string) (float32, error) {

	// I think have better way to do somethig like this in GO
	user := svc.UserService.GetUserById(userId)

	// I not create other class to get Product from db because not have rule for product at this time
	// Just for save time in this challenge xD
	product := &prod.Product{Id: productId}

	// How i dont have product here, I just will calculate percentage and not entire discount class
	// Saving time again
	discount := &disc.Discount{Percentage: 0.0}

	chain := svc.RuleService.BuildRuleChain()
	chain.Execute(user, product, discount)

	return discount.Percentage, nil
}
