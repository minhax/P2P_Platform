package com.lo23.communication.Messages;

import com.lo23.common.filehandler.FileHandlerInfos;

/**
 * Classe abstraite qui décrit le fichier
 */
public abstract class FileMessage extends Message
{
  /**
   * file : descripteur de fichier
   */
  protected FileHandlerInfos file;

  /**
   * l'accesseur (getter) de FileHandlerInfos
   * @return objet de type FileHandlerInfos
   */
  public FileHandlerInfos getFile()
  {
    return file;
  }
  /**
   * treatment : méthode abstraite à utiliser dans les sous classes
   */
  public abstract void treatment();
}
