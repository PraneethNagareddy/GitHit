import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';
import "../css/Login.css";
import * as Constants from '../constants/CommonConstants.js';

class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
    	  username: "",
      password: "",
      session_id : ""
    };
  }

  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();
    var apiURL = Constants.GITHIT_APPLICATION_BASE_URL+Constants.LOGIN_API_URL;
    var self = this;
    var payload={
    		"email_id":this.state.username,
    		"password":this.state.password
    }
    axios.post(apiURL, payload)
    		.then(function (response) {
    			console.log(response);
    			if(response.data.status == 200
    					&& typeof response.data.data.session_id !== 'undefined'  
    					&& response.data.data.session_id !== null){
   
    				console.log("Login successfull");
    				localStorage.setItem('session_id', response.data.data.session_id);
    				self.props.onLogin();
    			}
    			else {
    				alert("Unable to Login");
    				localStorage.setItem('session_id', "");
    				console.log("Unable to Login");
    			}
    		}).catch(function (error) {
    			console.log(error);
    		});
  }

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="username">
            <FormControl
              autoFocus
              type="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password">
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}

export default Login;
