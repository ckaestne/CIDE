package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Define extends CodeUnit_TopLevel {
  public Define(PPDefineStatement pPDefineStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPDefineStatement>("pPDefineStatement", pPDefineStatement)
    }, firstToken, lastToken);
  }
  public Define(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Define(cloneProperties(),firstToken,lastToken);
  }
  public PPDefineStatement getPPDefineStatement() {
    return ((PropertyOne<PPDefineStatement>)getProperty("pPDefineStatement")).getValue();
  }
}
