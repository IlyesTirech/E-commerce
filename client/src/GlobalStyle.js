import {createGlobalStyle} from 'styled-components'

const GlobalStyle = createGlobalStyle`

    :root{
        /* Colours */
        --default-background-color: #ffffff;
        --product-title-color: #202020;
        --product-brand-color: #4d4d4ddc;
        --product-price-color: #292929e6;
        /* Fonts */
        --default-text-font: 'Varela', sans-serif;
        --title-text-font: 'Roboto Slab', serif;
        --logo-text-font: 'Lobster Two', cursive;
    }


    *{
        text-decoration: none;
        margin: 0;
        padding: 0;
        box-sizing:border-box;

    }
    li{
        text-decoration: none;
        list-style: none;
    }
    a{
        text-decoration: none;
        color: #202020;
        font-family: var(--default-text-font);
    }
`

export default GlobalStyle