import React, {Component} from "react";
import {Jumbotron} from 'react-bootstrap';

export default class Welcome extends Component{
    render() {
        return (
            <Jumbotron className="bg-dark text-white">
                <h1>Welcome to Ripcar Service</h1>
                <p>
                    Great company, good stuff, good staff, low prices and quality work.
                </p>
            </Jumbotron>
        );
    }
}