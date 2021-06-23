import React from 'react'
import Col from 'react-bootstrap/Col'
import { Container } from 'react-bootstrap';
import { Row } from "react-bootstrap";
import Form from 'react-bootstrap/Form'
import { Button } from 'react-bootstrap';
import styled from "styled-components";
import * as fa from "react-icons/fa";

const Footer = () => {
    return (
        <FooterComp>
            <Container>
                <Row className="mt-4 mb-5 d-flex justify-content-center">
                    <Col xs={6} sm={6} md={6} lg={4} xl={4}>
                        <h2>News Letter</h2>
                        <p>Join our news letter to stay up to dat with all new releases!</p>
                    </Col>
                    <Col xs={8} sm={6} md={6} lg={4} xl={4} className=" d-flex flex-column justify-content-center"> 
                        <Form>
                            <Form.Group className="mb-2" controlId="formBasicEmail">
                                <Form.Control type="email"  placeholder="Email" />
                            </Form.Group>
                            <Form.Group className="mb-2" controlId="formBasicPassword">
                                <Form.Control type="text" placeholder="Name" />
                            </Form.Group>
                            
                            <Button variant="outline-dark" type="submit">
                                Submit
                            </Button>
                        </Form>
                    </Col>
                </Row>
                <Row className="mb-4">
                    <Col className=" d-flex justify-content-center"> 
                        <div className="d-flex footer-social-media-wrapper">
                            <fa.FaTwitter className="m-1"/>
                            <fa.FaFacebook className="m-1"/>
                            <fa.FaLinkedin className="m-1"/>
                        </div>
                    </Col>
                </Row>
                <Row className="mb-4">
                    <Col className=" d-flex justify-content-center"> 
                        <p>@ Copyriting MOQN. All Rights Reserved</p>
                    </Col>
                </Row>
            </Container>
        </FooterComp>
    )
}

export default Footer

const FooterComp = styled.div`



border-top: 0.1px solid #f1f1f1;
width:75%;
margin-inline: auto;
margin-top: 2em;



`