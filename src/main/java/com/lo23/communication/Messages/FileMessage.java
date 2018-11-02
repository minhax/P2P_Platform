package com.lo23.communication.Messages;

import com.lo23.common.filehandler.FileHandlerInfos;

public abstract class FileMessage {
  protected FileHandlerInfos file;
  
  public FileHandlerInfos getFile() {
    return file;
  }
}
