package rule

import (
	"github.com/herbert/sample-rule-engine/internal/business/discount/model"
	"github.com/herbert/sample-rule-engine/internal/business/product"
	"github.com/herbert/sample-rule-engine/internal/business/user"
)

type Rule interface {
	Execute(u *user.User, p *product.Product, d *model.Discount)
	SetNext(r Rule)
}
