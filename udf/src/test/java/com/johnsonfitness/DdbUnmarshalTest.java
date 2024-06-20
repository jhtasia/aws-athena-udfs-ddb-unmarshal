package com.johnsonfitness;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DdbUnmarshalTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DdbUnmarshalTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DdbUnmarshalTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testUnmarshal()
    {
        DdbUnmarshal handler = new DdbUnmarshal();

        String marshal = "{\"hello\":{\"S\":\"world\"},\"GG\":{\"S\":\"yy\"}}";
        String unmarshal = handler.unmarshal(marshal);
        String expected = "{\"GG\":\"yy\",\"hello\":\"world\"}";
        assertTrue(unmarshal.equals(expected));
    }

    public void testAllTypes()
    {
        DdbUnmarshal handler = new DdbUnmarshal();

        String marshal = "{\"thumbnail\":{\"S\":\"https://dbr0yfa9wws2a.cloudfront.net/e0608b43-f056-4912-8996-f361f00d9576-1647224077404\"},\"regions\":{\"L\":[{\"S\":\"GLOBAL\"}]},\"updatedBy\":{\"s\":\"dianachang@johnsonfitness.com\"},\"licensestarttime\":{\"S\":\"2022-02-28T16:00:00.000Z\"},\"created\":{\"S\":\"2022-03-14T02:14:51.097Z\"},\"licenseendtime\":{\"S\":\"2023-05-06T15:59:59.000Z\"},\"itemstatus\":{\"S\":\"ACTIVE\"},\"_type\":{\"S\":\"MEDIAIMAGE\"},\"filtertype\":{\"S\":\"MEDIAIMAGE\"},\"filternames\":{\"S\":\"#VOD005\"},\"provider\":{\"S\":\"JOHNSON\"},\"vendor\":{\"M\":{\"bucket\":{\"S\":\"atsoul-workout-prestage\"},\"service\":{\"S\":\"S3\"},\"objectkey\":{\"S\":\"e0608b43-f056-4912-8996-f361f00d9576-1647224077404\"}}},\"sk\":{\"S\":\"MEDIAIMAGE#D5HZCFM5109G0EJ4WDQRPRCB5Y\"},\"name\":{\"S\":\"vod005\"},\"assetstatus\":{\"S\":\"CREATED\"},\"filterstrings\":{\"S\":\"#RATIO#16:9#S#ACTIVE#R#GLOBAL\"},\"pk\":{\"S\":\"MEDIAIMAGE#D5HZCFM5109G0EJ4WDQRPRCB5Y\"},\"id\":{\"S\":\"D5HZCFM5109G0EJ4WDQRPRCB5Y\"},\"updated\":{\"S\":\"2022-03-14T02:14:51.097Z\"},\"ratio\":{\"S\":\"16:9\"}}";
        String unmarshal = handler.unmarshal(marshal);
        String expected = "{\"thumbnail\":\"https://dbr0yfa9wws2a.cloudfront.net/e0608b43-f056-4912-8996-f361f00d9576-1647224077404\",\"regions\":[\"GLOBAL\"],\"updatedBy\":\"dianachang@johnsonfitness.com\",\"licensestarttime\":\"2022-02-28T16:00:00.000Z\",\"created\":\"2022-03-14T02:14:51.097Z\",\"licenseendtime\":\"2023-05-06T15:59:59.000Z\",\"itemstatus\":\"ACTIVE\",\"_type\":\"MEDIAIMAGE\",\"filtertype\":\"MEDIAIMAGE\",\"filternames\":\"#VOD005\",\"provider\":\"JOHNSON\",\"vendor\":{\"bucket\":\"atsoul-workout-prestage\",\"service\":\"S3\",\"objectkey\":\"e0608b43-f056-4912-8996-f361f00d9576-1647224077404\"},\"sk\":\"MEDIAIMAGE#D5HZCFM5109G0EJ4WDQRPRCB5Y\",\"name\":\"vod005\",\"assetstatus\":\"CREATED\",\"filterstrings\":\"#RATIO#16:9#S#ACTIVE#R#GLOBAL\",\"pk\":\"MEDIAIMAGE#D5HZCFM5109G0EJ4WDQRPRCB5Y\",\"id\":\"D5HZCFM5109G0EJ4WDQRPRCB5Y\",\"updated\":\"2022-03-14T02:14:51.097Z\",\"ratio\":\"16:9\"}";
        assertTrue(unmarshal.equals(expected));

    }
}
