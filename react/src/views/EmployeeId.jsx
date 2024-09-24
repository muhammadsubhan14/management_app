import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function EmployeeId() {
  const { id } = useParams();
  const [employee, setEmployee] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedImage, setSelectedImage] = useState(null);
  const [uploadError, setUploadError] = useState(null);

  useEffect(() => {
    const fetchEmployee = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/employee/${id}`
        );
        setEmployee(response.data);
      } catch (error) {
        const errorMessage = error.response?.data?.message || error.message;
        setError(errorMessage);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployee();
  }, [id]);

  const handleImageChange = (event) => {
    setSelectedImage(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (!selectedImage) {
      setUploadError("Please select an image to upload.");
      return;
    }

    const formData = new FormData();
    formData.append("picture", selectedImage);
    formData.append("name", employee.name);
    formData.append("position", employee.position);
    formData.append("reportsTo", employee.reportsTo);

    try {
      await axios.put(`http://localhost:8080/api/employee/${id}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      alert("Image uploaded successfully!");
      fetchEmployee();
    } catch (error) {
      const errorMessage = error.response?.data?.message || error.message;
      setUploadError(errorMessage);
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-lg">Loading employee details...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="bg-red-500 text-white p-4 rounded">Error: {error}</div>
      </div>
    );
  }

  if (!employee) {
    return (
      <div className="flex justify-center items-center h-screen">
        <p className="text-lg">Employee not found.</p>
      </div>
    );
  }

  return (
    <div className="flex justify-center px-4 py-8">
      <div className="max-w-lg w-full rounded-lg shadow-lg overflow-hidden bg-white">
        <img
          className="w-full object-cover"
          src={employee.picture || "https://tailwindcss.com/img/card-top.jpg"}
          alt={employee.name}
        />
        <div className="p-6">
          <h2 className="font-bold text-2xl mb-2 text-gray-800 text-center">
            {employee.name}
          </h2>
          <p className="text-gray-600 text-lg mb-2 text-center">
            {employee.position}
          </p>
          <p className="text-gray-500 text-sm text-center">
            <span className="font-bold">Reports To: </span>
            {employee.reportsTo}
          </p>
        </div>
        {uploadError && (
          <div className="bg-red-500 text-white p-4 rounded mx-6 my-2 text-center">
            {uploadError}
          </div>
        )}
        <div className="p-4 flex flex-col items-center">
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            className="mb-2 border border-gray-300 rounded p-2 w-full"
          />
          <button
            onClick={handleUpload}
            className="btn btn-primary bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 transition duration-200 mt-5"
          >
            Upload Image
          </button>
        </div>
      </div>
    </div>
  );
}
