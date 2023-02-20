import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
const StarRating = ({rating, reviews}) => {
    
    console.log("rating is");
    console.log((rating));
     const intStars= parseInt(rating);
     const partStars= rating - intStars;
     const [starArray, setStarArray]= useState([0,0,0,0,0]);
     let miniRating= false;
 
   
     useEffect(() => {
        // OTRO METODO PARA GENERAR EL ARRAY
    //   setStarArray(initArray.fill(0,(intStars)));
    //   setStarArray(starArray.splice((intStars),1,(partStars*100)));
    //   console.log(starArray);
    setStarArray(getStars());
    //setInterval(getStars)
     
      
     }, [])
     
    const getStars = ()=>{
        starArray.map((n, index)=>{
            if(index<intStars){
                starArray[index]=100;
            }
            else if(index==intStars){
               starArray[index]=partStars*100;
            }
        })
        console.log("a");
        console.log(starArray);
        return [...starArray];
    }

  return (
    <>
    <div className='flex flex-row  justify-center py-3'>
    {starArray.map((p)=>{console.log(p);
        return (<svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" width="25px" height="25px" viewBox="0 0 32 32">
        <defs>
          <linearGradient id={`grad-${p}`}>
            <stop offset={`${p}%`} stop-color="white"/>
            <stop offset="0%" stop-color="grey"/>
          </linearGradient>
        </defs>
        <path fill={`url(#grad-${p})`} d="M20.388,10.918L32,12.118l-8.735,7.749L25.914,31.4l-9.893-6.088L6.127,31.4l2.695-11.533L0,12.118
      l11.547-1.2L16.026,0.6L20.388,10.918z"/>
      </svg>)
    })} {miniRating?<span className='text-[16px]'>({rating})</span>:<span className='text-[16px]'>({reviews})</span>}
    </div>
    
    </>
  )
}

export default StarRating