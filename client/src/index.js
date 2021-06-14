import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
//Redux Setup
import {createStore, applyMiddleware,compose} from 'redux';
import rootReducer from './reducer';
import {Provider} from 'react-redux'
import thunk from 'redux-thunk'

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

const store = createStore(rootReducer,
  composeEnhancer(applyMiddleware(thunk))
  )

ReactDOM.render(
  <React.StrictMode>
    <Provider store = {store}>
    <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);


