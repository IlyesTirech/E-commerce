import React, { useState, useEffect } from "react";
import styled from "styled-components";
import * as fa from "react-icons/fa";
import { Row, Col, Button, Jumbotron, Container } from "react-bootstrap";

import UserService from "../services/user.service";
import { useSelector, useDispatch } from "react-redux";
import {loadProducts} from '../actions/productAction'
const Home = () => {

  const dispatch = useDispatch();
  const {products} = useSelector(state => state.product)


  const amountOfImages = (length) =>{
    if(length > 1){
      return length - 1
    }
    return 0
  }

  useEffect(() => {
    dispatch(loadProducts())
  }, []);

  return (

    <>
      <Jumbotron fluid className="bg-light mb-5">
        <AdSection>
          <Container className="">
            <Row className="d-flex justify-content-between align-items-center">
              <Col xs={12} sm={12} md={12} lg={6} xl={6} className="d-flex justify-content-center img-wrap">
                <img src="https://images.pexels.com/photos/6908553/pexels-photo-6908553.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" />
              </Col>
              <Col xs={10} sm={10} md={10} lg={5} xl={5} className="bg-white mt-5 mb-5">
            <h1 className="mb-2 mt-5">Slogoskon</h1>
            <p className="mb-4">
             Limited edition items sell out really fast and rarely re-appear, do not miss your chance! 
            </p>
                <Button className="h-25 m-3 mb-5" variant="outline-dark">SHOP NOW</Button>
              </Col>
              
            </Row>
            <p>
            </p>
          </Container>
        </AdSection>
      </Jumbotron>
      <CollectionDisplay>
        <Row className="mx-auto mb-3">
          <Col >
          <h2 className="collection-headline-title mb-3">Collections</h2>
            <p>Most popular collections</p>
          </Col>
        </Row>
        <Row>
          <Col xs={12} sm={8} md={6} lg={5} xl={5} className="mx-auto mb-5">
            <div className="d-flex justify-content-center">
              <img src="https://images.pexels.com/photos/2647714/pexels-photo-2647714.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260" alt="" />
              <div className="d-flex m-4 flex-column">
                <h6>COLLECTION</h6>
                <h2>Tables</h2>
              </div>
            </div>
          </Col>
          <Col xs={12} sm={8} md={6} lg={5} xl={5} className="mx-auto mb-5">
            <div className="d-flex justify-content-center">
              <img src="https://images.pexels.com/photos/1112598/pexels-photo-1112598.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" />
              <div className="d-flex m-4 flex-column">
                <h6>COLLECTION</h6>
                <h2>Lamps</h2>
              </div>
            </div>
          </Col>
          <Col xs={12} sm={8} md={6} lg={5} xl={5} className="mx-auto mb-5">
            <div className="d-flex justify-content-center">
              <img src="https://images.pexels.com/photos/1957477/pexels-photo-1957477.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" />
              <div className="d-flex m-4 flex-column">
                <h6>COLLECTION</h6>
                <h2>Chairs</h2>
              </div>
            </div>
          </Col>
          <Col xs={12} sm={8} md={6} lg={5} xl={5} className="mx-auto mb-5">
            <div className="d-flex justify-content-center">
              <img src="https://images.pexels.com/photos/5799379/pexels-photo-5799379.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" />
              <div className="d-flex m-4 flex-column">
                <h6>COLLECTION</h6>
                <h2>Clocks</h2>
              </div>
            </div>
          </Col>

        </Row>
        <Row className="d-flex justify-content-center  mb-5">
            <Button className="w-25" size="md" variant="outline-dark">View all collections</Button>

        </Row>
      </CollectionDisplay>

      <Jumbotron fluid className="bg-light mb-5">
        <AdSection>
          <Container className="">
            <Row className="d-flex justify-content-between align-items-center">
              <Col xs={10} sm={10} md={6} lg={5} xl={5} className="bg-white mt-5 mb-5">
            <h1 className="mb-2 mt-5">The new collection</h1>
            <p className="mb-4">
              View our latest chair collection from the best designers around the world
            </p>
                <Button className="h-25 m-3 mb-5" variant="outline-dark">SHOP NOW</Button>
              </Col>
              
              <Col xs={12} sm={12} md={6} lg={6} xl={6} className="d-flex justify-content-center  img-wrap">
                <img src="https://images.pexels.com/photos/279648/pexels-photo-279648.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" />
              </Col>
            </Row>
            <p>
            </p>
          </Container>
        </AdSection>
      </Jumbotron>

      <ProductDisplay className="container">
        <Row className="mb-5 mt-4">
          <div className="d-flex justify-content-between align-items-center">
            <div className="d-flex justify-content-between align-items-center">
              <h2 className="products-headline-title">Products</h2>
            </div>
            <div className="d-flex justify-content-around align-items-center">
              <a href="#" className="m-2">Previous</a>
              <p className="m-2">|</p>
              <a href="#" className="m-2">Next</a>
            </div>
          </div>
        </Row>
        <Row className="justify-content-md-center">
          {
            products.length > 0 ? 
            <>
            { products.slice(products.length - 8).map(product => (
              <Col xs={12} sm={9} md={4} lg={3} xl={3} className="mx-auto">
                <div className="product-grid mb-5">
                    <div className="product-image mb-4">
                        <a href="#" className="image">
                          { product.images.length > 0 ? 
                          <>
                            {product.images.slice(amountOfImages(product.images.length)).map((image, index)  =>(
                              <img className={``} src={`https://images.pexels.com/photos/447592/pexels-photo-447592.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260`} alt="" />
                            ))}
                          </>
                          : 
                          <>
                           <img className="" src={`https://images.pexels.com/photos/447592/pexels-photo-447592.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260`} alt="" />  
                          </>
                          }
                        </a>
                        
                    </div>
                    <div className="product-content">
                      <div className="product-content-wrap d-flex p-1 justify-content-between">
                        <a className="product-title mx-1" href="#">Sklovan</a>
                        <a className="product-brand mx-1" href="#">Croisant</a>
                      </div>
                        <p className="product-price mx-2">£ {product.price}</p>
                    </div>
                  </div>    
              </Col>
            ))}
            </>
            : null}
        </Row>
      </ProductDisplay>
    </>
  );
};


const AdSection = styled.div`
img {
  display: block;
  max-width:100%;
  width:100%;
  min-height:400px;
  max-height: 800px;
  height: auto;
}
.img-wrap{
  padding: 0;
}

`
const CollectionDisplay = styled.div`
img {
  display: block;
  max-width:100%;
  min-height:300px;
  max-height: 300px;
  width: 225px;
  height: auto;
}

`
const ProductDisplay = styled.div`
img {
  display: block;
  max-width:100%;
  min-height:300px;
  max-height: 300px;
  width: auto;
  height: auto;
}

.products-headline-title{
  font-family: var(default-text-font);
  font-weight: 600;
}
.product-grid{
  max-width: 250px;
  margin-inline: auto;
}

.product-title{
  color: var(--product-title-color);
  font-weight: 700;
}
.product-price{
  color: var(--product-price-color);
}
.product-brand{ 
  color: var(--product-brand-color);
}

`






export default Home;