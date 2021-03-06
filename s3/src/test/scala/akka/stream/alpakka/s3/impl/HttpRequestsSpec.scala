/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package akka.stream.alpakka.s3.impl


import akka.http.scaladsl.model.{ HttpEntity, MediaTypes }
import akka.http.scaladsl.model.headers.RawHeader
import akka.stream.alpakka.s3.acl.CannedAcl
import org.scalatest.{ FlatSpec, Matchers }

class HttpRequestsSpec extends FlatSpec with Matchers {
  it should "initiate multipart upload" in {
    val location = S3Location("bucket", "image-1024@2x")
    val contentType = MediaTypes.`image/jpeg`
    val acl = CannedAcl.PublicRead
    val req = HttpRequests.initiateMultipartUploadRequest(location, contentType, acl)

    req.entity shouldEqual HttpEntity.empty(contentType)
    req.headers should contain(RawHeader("x-amz-acl", acl.value))
  }
}
