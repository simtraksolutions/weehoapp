import Head from 'next/head'
import Image from 'next/image'

export default function Home() {
  const glimpseLinks = [
    { url: "https://drive.google.com/drive/folders/1iXqFNUjCWsx3YMUBubmlbPbQnvKu5jII?sort=13&direction=a", label: "View Glimpse" },
  ];  

  return (
    <>
      <Head>
        <title>Mandeep Singh - Singer</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header Section */}
        <header className="flex justify-between items-center py-6">
          <div className="flex items-center justify-center space-x-4">
            <Image src="/images/Weeho.png" alt="Logo" width={105} height={105} className="rounded-full" />
          </div>
          <h1 className="text-3xl font-bold flex justify-center">Mandeep Singh</h1>
          <div className="w-10 h-10">
            <Image src="/images/profile-icon.jpeg" alt="Profile Icon" width={40} height={40} className="rounded-full" />
          </div>
        </header>

        {/* Singer Profile Section */}
        <section className="text-center my-12">
          <Image src="/images/mandeep.jpg" alt="Mandeep Singh" width={250} height={250} className="rounded-full mx-auto" />
          <h2 className="text-2xl font-semibold my-4 ">Greetings from WEEHO</h2>
          <p className="text-base text-gray-600 my-4">
            WEEHO provides a robust online platform to showcase your skills, whether it be singing, dancing, playing any instrument, or any other performing arts. Our platform is accessible to people from all walks of life, and we are always on the lookout for new and exciting talents to feature on our online platform.
          </p>
          <a href="bookanevent.html" className="inline-block bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded-full my-4">Book an Event</a>
        </section>

        {/* About Naviin Gandharv Section */}
        <div className="bg-gray-100 rounded-lg p-6 my-12">
          <Image src="/images/mandeep2.jpg" alt="Mandeep Singh" width={250} height={250} className="rounded-full mr-6" />
          <h2 className="text-2xl font-semibold my-4">Mandeep Singh</h2>
          <h3 className="text-lg text-gray-500">An Electrifying Singer</h3><br></br>
          <h2 className="text-lg text-blue-500 font-bold">About Mandeep Singh</h2>
          <p className="text-base text-gray-600 my-4">
            I am the lead singer of a high-energy three-piece band hailing from Udaipur, specialising in delivering the perfect blend of Hindi and Punjabi Tadka. With our dynamic lineup of drums, guitar, and vocals, we create an electrifying musical experience that leaves audiences mesmerised.
          </p>
          <h2 className="text-lg text-blue-500 font-bold">Contribution to Music</h2>
          <p className="text-base text-gray-600 my-4">
            From soulful renditions to foot-tapping beats, our performances are a celebration of the rich cultural heritage of Hindi and Punjabi music.
          </p>
          <h2 className="text-lg text-blue-500 font-bold">Join our Musical Journey</h2>
          <p className="text-base text-gray-600 my-4">
            With my versatile vocals and engaging stage presence, I lead our band in delivering unforgettable performances that will have you singing and dancing along. Get ready to groove to the infectious rhythms of our unique musical journey!
          </p>
          <>
            <h2 className="text-lg text-blue-500 font-bold">Glimpse Links:</h2>
            <ul>
              {glimpseLinks.map((link, index) => (
                <li key={index}>
                  <a href={link.url} style={{ color: 'red' }}>{link.label}</a>
                </li>
              ))}
            </ul>
          </>
        </div>
      </div>
    </>
  )
}