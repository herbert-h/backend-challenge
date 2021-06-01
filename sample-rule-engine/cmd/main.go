package main

import "github.com/herbert/sample-rule-engine/cmd/application"

func main() {
	server := application.NewApp()
	// todo can use channels here
	go server.StartGrpc()
	server.StartHttp()
}
