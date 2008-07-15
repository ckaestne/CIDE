package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfDefBL extends CodeUnit_InBlock {
  public IfDefBL(PPIfDef_BlockLevel pPIfDef_BlockLevel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPIfDef_BlockLevel>("pPIfDef_BlockLevel", pPIfDef_BlockLevel)
    }, firstToken, lastToken);
  }
  public IfDefBL(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfDefBL(cloneProperties(),firstToken,lastToken);
  }
  public PPIfDef_BlockLevel getPPIfDef_BlockLevel() {
    return ((PropertyOne<PPIfDef_BlockLevel>)getProperty("pPIfDef_BlockLevel")).getValue();
  }
}
