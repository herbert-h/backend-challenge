package rule

import (
	"time"

	disc "github.com/herbert/sample-rule-engine/internal/business/discount/model"
	prod "github.com/herbert/sample-rule-engine/internal/business/product/model"
	user "github.com/herbert/sample-rule-engine/internal/business/user/model"
)

type BlackFriday struct {
	Next Rule
}

const (
	bfDay   = 25
	bfMonth = 11
)

func (b *BlackFriday) Execute(u *user.User, p *prod.Product, d *disc.Discount) {
	today := time.Now()
	if today.Day() == bfDay && today.Month() == bfMonth {
		d.Percentage += 10.0
	}
	b.Next.Execute(u, p, d)
}

func (b *BlackFriday) SetNext(nextRule Rule) {
	b.Next = nextRule
}
