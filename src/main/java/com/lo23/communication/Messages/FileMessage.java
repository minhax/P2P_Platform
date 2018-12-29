package com.lo23.communication.Messages;

import com.lo23.common.filehandler.FileHandlerInfos;

public abstract class FileMessage extends Message
{
  protected FileHandlerInfos file;
  
  public FileHandlerInfos getFile()
  {
    return file;
  }
  public abstract void treatment();
}
