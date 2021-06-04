package rule

import (
	disc "github.com/herbert/sample-rule-engine/internal/business/discount/model"
	prod "github.com/herbert/sample-rule-engine/internal/business/product/model"
	user "github.com/herbert/sample-rule-engine/internal/business/user/model"
)

type Rule interface {
	Execute(u *user.User, p *prod.Product, d *disc.Discount)
	SetNext(r Rule)
}
