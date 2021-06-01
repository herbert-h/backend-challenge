package util

import "strings"

type QueryBuilder struct {
	Value string
}

func (s *QueryBuilder) Bind(pattern string, value string) QueryBuilder {
	return QueryBuilder{strings.Replace(s.Value, ":"+pattern, value, -1)}
}
