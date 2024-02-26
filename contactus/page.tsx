import Image from 'next/image';
import Link from 'next/link';

export default function ContactusPage() {
  return (
    <div className="flex justify-center items-center min-h-screen bg-white">
      <div className="w-full max-w-md p-8 space-y-4 bg-gray-100 rounded-lg shadow">
        <div className="flex justify-center">
          <Image src="/images/Weeho.png" alt="Logo" width={130} height={50} />
        </div>
        <form action="bookanevent.php" method="post" className="space-y-4">
          <h2 className="text-3xl font-bold text-center">Fill the form</h2>
          <h2 className="text-md text-center">Please enter below details</h2>
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
            <Image src="/images/schedule.png" alt="" width={24} height={24} />
            <input type="text" name="message" id="message" placeholder="Message" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <button type="submit" className="w-full bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 rounded-full">SUBMIT</button>
        </form>
        
        {/* Contact us section */}
        <div className="text-center">
          <p className="text-lg font-bold">Contact us</p>
          <p className="text-xl">+91 9883585647</p>
          <p className="text-xl">contact@weeho.in</p>
        </div>
      </div>
    </div>
  );
}
