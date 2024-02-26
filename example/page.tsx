import Head from 'next/head'
import Image from 'next/image'
import next from 'public/2nd.jpg';
export default function Home() {
  return (
    <>
      <Head>
        <title>Naviin Gandharv - Singer</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header Section */}
        <header className="flex justify-between items-center py-6">
          <div className="flex items-center justify-center space-x-4">
            <Image src="/images/Weeho.png" alt="Logo" width={105} height={105} className="rounded-full" />
          </div>
          <h1 className="text-3xl font-bold flex justify-center">Naviin Gandharv</h1>
          <div className="w-10 h-10">
            <Image src="/images/profile-icon.jpeg" alt="Profile Icon" width={40} height={40} className="rounded-full" />
          </div>
        </header>

        {/* Singer Profile Section */}
        <section className="text-center my-12">
          <Image src="/images/naviin.jpg" alt="Naviin Gandharv" width={250} height={250} className="rounded-full mx-auto" />
          <h2 className="text-2xl font-semibold my-4 ">Greetings from WEEHO</h2>
          <p className="text-base text-gray-600 my-4">
            WEEHO provides a robust online platform to showcase your skills, whether it be singing, dancing, playing any instrument, or any other performing arts. Our platform is accessible to people from all walks of life, and we are always on the lookout for new and exciting talents to feature on our online platform.
          </p>
          <a href="bookanevent.html" className="inline-block bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-full my-4">Book an Event</a>
        </section>

        {/* About Naviin Gandharv Section */}
        <div className="bg-gray-100 rounded-lg p-6 my-12">
          <video controls className="w-full rounded">
            <source src="/images/naviinsingervideo.mp4" type="video/mp4" />
          </video>
          <h2 className="text-2xl font-semibold my-4">Naviin Gandharv</h2>
          <h3 className="text-lg text-gray-500">An Expert in Belabaharr</h3>
          <p className="text-base text-gray-600 my-4">
            Pt.Naviin Gandharv is a young, multi-talented and dynamic artist who plays the unique instrument- Belabaharr & Tabla with equal dexterity...
          </p>
        </div>

        {/* Image Gallery */}
        <div className="grid grid-cols-5 gap-4 my-12">
          {[...Array(25).keys()].map(i => (
            <Image key={i} src={`/images/np${i+1}.jpeg`} alt={`Image ${i+1}`} width={110} height={110} className="object-cover" />
          ))}
        </div>
      </div>
    </>
  )
}