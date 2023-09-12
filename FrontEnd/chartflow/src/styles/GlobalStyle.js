import { createGlobalStyle } from "styled-components";
import reset from "styled-reset";

const GlobalStyle = createGlobalStyle`
    ${reset}
    * {
        font-family: 'SUIT-Regular';
        @font-face {
        font-family: 'SUIT-Regular';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
        font-weight: normal;
        font-style: normal;
        } 
    }
    body{
        background: ${({ theme }) => theme.bg};
        color: ${({ theme }) => theme.font.deep};
        margin: 10vh 15vw; 
        font-family: 'SUIT-Regular';
        @font-face {
        font-family: 'SUIT-Regular';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
        font-weight: normal;
        font-style: normal;
        }  
    }
`;

export default GlobalStyle;
