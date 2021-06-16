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
              <div>
                {
                  product.images > 0 ?
                  <div>
                    {
                      product.images.map(image => (
                        <img src={`http://localhost:8080${image}`} />
                      ))
                    }
                    </div>
                    :
                    null
                }
              </div>
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