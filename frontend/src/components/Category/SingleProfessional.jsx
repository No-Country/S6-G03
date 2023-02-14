import React from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";

const SingleProfessional = () => {
  const { id } = useParams();
  const [oneProfessional, setOneProfessional] = useState("");

  useEffect(() => {
    const url = `${import.meta.env.VITE_BACKEND_URL}/api/profesional/${id}`;

    axios
      .get(url)
      .then((res) => setOneProduct(res.data))
      .catch((err) => console.log(err));
  }, []);

  return <div>SingleProfessional</div>;
};

export default SingleProfessional;
