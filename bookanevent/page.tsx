import Image from 'next/image';
import Link from 'next/link';

export default function BookaneventPage() {
  const indianStates = [
    "Andhra Pradesh",
    "Arunachal Pradesh",
    "Assam",
    "Bihar",
    "Chhattisgarh",
    "Goa",
    "Gujarat",
    "Haryana",
    "Himachal Pradesh",
    "Jharkhand",
    "Karnataka",
    "Kerala",
    "Madhya Pradesh",
    "Maharashtra",
    "Manipur",
    "Meghalaya",
    "Mizoram",
    "Nagaland",
    "Odisha",
    "Punjab",
    "Rajasthan",
    "Sikkim",
    "Tamil Nadu",
    "Telangana",
    "Tripura",
    "Uttar Pradesh",
    "Uttarakhand",
    "West Bengal",
    "Andaman and Nicobar Islands",
    "Chandigarh",
    "Dadra and Nagar Haveli",
    "Daman and Diu",
    "Lakshadweep",
    "Delhi",
    "Puducherry"
  ];

  return (
    <div className="flex justify-center items-center min-h-screen bg-white">
      <div className="w-full max-w-md p-8 space-y-4 bg-gray-100 rounded-lg shadow">
        <div className="flex justify-center">
          <Image src="/images/Weeho.png" alt="Logo" width={130} height={50} />
        </div>
        <form action="bookanevent.php" method="post" className="space-y-4">
          <h2 className="text-2xl font-bold text-center">Book an Event</h2>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/user.png" alt="" width={24} height={24} />
            <input type="text" name="name" id="name" placeholder="Name" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/telephone.png" alt="" width={24} height={24} />
            <input type="tel" name="tel" id="tel" placeholder="Phone number" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/email.png" alt="" width={24} height={24} />
            <input type="email" name="email" id="email" placeholder="Email" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/location.png" alt="" width={24} height={24} />
            <input type="text" name="city" id="city" placeholder="City" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/list.png" alt="" width={24} height={24} />
            <select name="state" id="state" className="flex-1 bg-transparent outline-none px-2" title="Select a State" defaultValue="">
              <option value="" disabled>Select a State</option>
              {indianStates.map((state, index) => (
                <option key={index} value={state}>{state}</option>
              ))}
            </select>
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/calendar (1).png" alt="" width={24} height={24} />
            <input type="date" name="eventDate" id="eventDate" title="eventDate" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/list.png" alt="" width={24} height={24} />
            <select name="occasion" id="occasion" className="flex-1 bg-transparent outline-none px-2" title="Select an Occasion" defaultValue="">
              <option value="" disabled>Select an Occasion</option>
              <option value="birthday">Birthday</option>
              <option value="wedding">Wedding</option>
              <option value="corporate">Corporate Event</option>
              <option value="other">Other</option>
            </select>
          </div>
          <button type="submit" className="w-full bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 rounded-full">Book now</button>
        </form>
      </div>
    </div>
  );
}