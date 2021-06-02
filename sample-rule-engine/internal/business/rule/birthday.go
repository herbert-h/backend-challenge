package rule

import (
	"time"

	"github.com/herbert/sample-rule-engine/internal/business/discount/model"
	"github.com/herbert/sample-rule-engine/internal/business/product"
	"github.com/herbert/sample-rule-engine/internal/business/user"
)

type Birthday struct {
	Next Rule
}

func (b *Birthday) Execute(u *user.User, p *product.Product, d *model.Discount) {
	if u != nil {
		bday, _ := time.Parse(time.RFC3339, u.DateOfBirth)
		today := time.Now()
		if bday.Day() == today.Day() && bday.Month() == today.Month() {
			d.Percentage += 5.0
		}
	}
	b.Next.Execute(u, p, d)
}

func (b *Birthday) SetNext(nextRule Rule) {
	b.Next = nextRule
}
