package com.lo23.communication.Messages;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerInfos;

public abstract class FileMessage extends Message{
  protected FileHandler file;
  
  public FileHandler getFile()
  {
    return file;
  }
  public abstract void treatment();
}
