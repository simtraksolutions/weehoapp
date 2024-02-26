import Image from 'next/image';
import Link from 'next/link';

export default function LoginPage() {
  return (
    <div className="flex justify-center items-center min-h-screen bg-white">
      <div className="w-full max-w-md p-8 space-y-4 bg-gray-100 rounded-lg shadow">
        <div className="flex justify-center">
          <Image src="/images/Weeho.png" alt="Logo" width={130} height={50} />
        </div>
        <form action="login.php" method="post" className="space-y-4">
          <h2 className="text-2xl font-bold text-center">Login</h2>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/email.png" alt="" width={24} height={24} />
            <input type="email" name="email" id="email" placeholder="Email" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="flex items-center border border-gray-300 rounded-full px-3 py-2">
            <Image src="/images/padlock.png" alt="" width={24} height={24} />
            <input type="password" name="password" id="password" placeholder="Password" className="flex-1 bg-transparent outline-none px-2" />
          </div>
          <div className="text-center">
            <Link href="/forgot-password">
              <span className="text-blue-500 cursor-pointer">Forgot password?</span>
            </Link>
          </div>
          <button type="submit" className="w-full bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 rounded-full">Login</button>
          <p className="text-center">Don't have an account? <Link href="/login"><span className="text-blue-500 cursor-pointer">Signup here</span></Link></p>
        </form>
      </div>
    </div>
  );
}
