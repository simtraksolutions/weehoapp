import Image from 'next/image';
import Link from 'next/link';


export default function HistoryPage() {
  return (
    <div className="flex justify-center items-center min-h-screen bg-white">
      <div className="w-full max-w-md p-8 space-y-4 bg-gray-100 rounded-lg shadow">
        <div className="flex justify-center">
          <Image src="/images/Weeho.png" alt="Logo" width={130} height={50} />
        </div>
        <form action="signup.php" method="post" className="space-y-4">
          <h2 className="text-3xl font-bold text-center">Booking History</h2>
          <div className="flex items-center px-3 py-2">
            <Image src="/images/telephone.png" alt="" width={24} height={24} />
            <strong className="flex-1 bg-transparent outline-none px-2">Phone Number:</strong>
          </div>
          <div className="flex items-center px-3 py-2">
            <Image src="/images/location.png" alt="" width={24} height={24} />
            <strong className="flex-1 bg-transparent outline-none px-2">City:</strong>
          </div>
          <div className="flex items-center px-3 py-2">
            <Image src="/images/calendar.png" alt="" width={24} height={24} />
            <strong className="flex-1 bg-transparent outline-none px-2">Event Date:</strong>
          </div>
          <div className="flex items-center px-3 py-2">
            <Image src="/images/famous.png" alt="" width={24} height={24} />
            <strong className="flex-1 bg-transparent outline-none px-2">Event:</strong>
          </div>
          <div className="flex items-center px-3 py-2">
            <Image src="/images/opportunity.png" alt="" width={24} height={24} />
            <strong className="flex-1 bg-transparent outline-none px-2">Occasion:</strong>
          </div>
        </form>
      </div>
    </div>
  );
}