package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfDefTL extends CodeUnit_TopLevel {
  public IfDefTL(PPIfDef_TopLevel pPIfDef_TopLevel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPIfDef_TopLevel>("pPIfDef_TopLevel", pPIfDef_TopLevel)
    }, firstToken, lastToken);
  }
  public IfDefTL(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfDefTL(cloneProperties(),firstToken,lastToken);
  }
  public PPIfDef_TopLevel getPPIfDef_TopLevel() {
    return ((PropertyOne<PPIfDef_TopLevel>)getProperty("pPIfDef_TopLevel")).getValue();
  }
}
