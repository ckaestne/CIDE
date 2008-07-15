package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DefineBL extends CodeUnit_InBlock {
  public DefineBL(PPDefineStatement pPDefineStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPDefineStatement>("pPDefineStatement", pPDefineStatement)
    }, firstToken, lastToken);
  }
  public DefineBL(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DefineBL(cloneProperties(),firstToken,lastToken);
  }
  public PPDefineStatement getPPDefineStatement() {
    return ((PropertyOne<PPDefineStatement>)getProperty("pPDefineStatement")).getValue();
  }
}
