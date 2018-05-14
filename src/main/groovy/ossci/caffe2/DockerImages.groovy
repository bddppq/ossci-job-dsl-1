package ossci.caffe2

class DockerImages {

  // NOTE this is the list of dockerImages, but not the list of all Jenkins
  // build jobs, as some jobs use the same dockerImage. The real source of all
  // possible jenkins jobs is the keys of the map
  //   imageOf<jenkinsBuildName, dockerImageName>
  // that is defined below
  static final List<String> images = [
    // Primary builds
    'py2-cuda8.0-cudnn7-ubuntu16.04',
    'py2-cuda9.0-cudnn7-ubuntu16.04',
    'py2-cuda9.1-cudnn7-ubuntu16.04',
    'py2-gcc5-ubuntu16.04',
    'py2-mkl-ubuntu16.04',

    // Primary builds (Python 3.5, available by default on Ubuntu)
    'py3.5-cuda8.0-cudnn7-ubuntu16.04',
    'py3.5-cuda9.0-cudnn7-ubuntu16.04',
    'py3.5-cuda9.1-cudnn7-ubuntu16.04',
    'py3.5-gcc5-ubuntu16.04',
    'py3.5-mkl-ubuntu16.04',

    // Python compatibility (Python 3.6 is not default)
    'py3.6-gcc5-ubuntu16.04',

    // Compatibility check for cuDNN 6 and 5 (build only)
    'py2-cuda8.0-cudnn6-ubuntu16.04',
    'py2-cuda8.0-cudnn5-ubuntu16.04',

    // Compiler compatibility for 14.04 (build only)
    'py2-gcc4.8-ubuntu14.04',
    'py2-gcc4.9-ubuntu14.04',

    // Compiler compatibility for 16.04 (build only)
    'py2-gcc6-ubuntu16.04',
    'py2-gcc7-ubuntu16.04',
    'py2-clang3.8-ubuntu16.04',
    'py2-clang3.9-ubuntu16.04',
    'py2-clang4.0-ubuntu16.04',
    'py2-clang5.0-ubuntu16.04',

    // Python 3 compiler compatibility
    'py3.5-gcc6-ubuntu16.04',
    'py3.5-gcc7-ubuntu16.04',
    'py3.5-clang3.8-ubuntu16.04',
    'py3.5-clang3.9-ubuntu16.04',
    'py3.5-clang4.0-ubuntu16.04',
    'py3.5-clang5.0-ubuntu16.04',

    // Build for Android
    'py2-android-ubuntu16.04',

    // Builds for Anaconda
    'conda2-ubuntu16.04',
    'conda2-cuda9.0-cudnn7-ubuntu16.04',
    'conda2-cuda8.0-cudnn7-ubuntu16.04',
    'conda3-ubuntu16.04',
    'conda3-cuda9.0-cudnn7-ubuntu16.04',
    'conda3-cuda8.0-cudnn7-ubuntu16.04',

    'conda2-gcc4.8-ubuntu16.04',
    'conda3-gcc4.8-ubuntu16.04',

    // CentOS images (Python 2 only)
    'py2-centos7',
    'py2-cuda8.0-cudnn7-centos7',
    'py2-cuda9.0-cudnn7-centos7',

    // AMD ROCM builds
    'py2-clang3.8-rocm1.7.1-ubuntu16.04',
    'py3.6-clang3.8-rocm1.7.1-ubuntu16.04',
  ];

  // Maps (BUILD_ENVIRONMENT == jenkins build name) --> dockerImage
  static final Map<String, String> imageOf = [:]
  static {
    // By default populated by all dockerImages pointing to themselves
    for (String dockerImage : images) {
      imageOf.put(dockerImage, dockerImage);
    }
    // Additional builds that re-use dockerImages below
    imageOf.put("conda2-integrated-ubuntu16.04", "conda2-ubuntu16.04")
    imageOf.put("conda3-integrated-ubuntu16.04", "conda3-ubuntu16.04")
    imageOf.put("conda2-cuda8.0-cudnn7-integrated-ubuntu16.04", "conda2-cuda8.0-cudnn7-ubuntu16.04")
    imageOf.put("conda2-cuda9.0-cudnn7-integrated-ubuntu16.04", "conda2-cuda9.0-cudnn7-ubuntu16.04")
    imageOf.put("conda2-cuda8.0-cudnn7-integrated-slim-ubuntu16.04", "conda2-cuda8.0-cudnn7-ubuntu16.04")
    imageOf.put("conda2-cuda9.0-cudnn7-integrated-slim-ubuntu16.04", "conda2-cuda9.0-cudnn7-ubuntu16.04")
    imageOf.put("py2-mkl-aten-ubuntu16.04", "py2-mkl-ubuntu16.04")
    imageOf.put("py2-cuda8.0-cudnn7-aten-ubuntu16.04", "py2-cuda9.0-cudnn7-ubuntu16.04")
    imageOf.put("py2-cuda9.0-cudnn7-aten-ubuntu16.04", "py2-cuda9.0-cudnn7-ubuntu16.04")

    // Verify that all docker images (values) in the map are valid
    for (String dockerImage : imageOf.values()) {
      assert dockerImage in images
    }
  }

  static final Collection<String> allJenkinsDockerJobs = imageOf.keySet();
}
