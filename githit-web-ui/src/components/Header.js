import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';

class Header extends Component{
	render(){
		return( <div style={{border:"1px solid black"}}>
					<h1>
						GitHit Application
					</h1>
				</div>
				);
	}
}

export default Header;