import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import { useSelector, useDispatch } from "react-redux";
import {loadProducts} from '../actions/productAction'
const Home = () => {

  const dispatch = useDispatch();
  const [content, setContent] = useState("");

  const {products} = useSelector(state => state.product)

  useEffect(() => {
    dispatch(loadProducts())
  }, []);

  return (
    <div>
    {
      products.length > 0 ?
      <div>
        {
          products.map(product => (
            <div>
              <h2>{product.name}</h2>
              <ul>
                
              {product.images.map((image, i) => <img className="w-25 h-25" src={`http://localhost:8080${image}`} key={i} />)}
                
              </ul>
              
              </div>           
          ))
        }
        </div>
        :
        null
    }
    </div>
  );
};

export default Home;