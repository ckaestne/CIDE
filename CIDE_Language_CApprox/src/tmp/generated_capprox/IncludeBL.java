package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IncludeBL extends CodeUnit_InBlock {
  public IncludeBL(PPIncludeStatement pPIncludeStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PPIncludeStatement>("pPIncludeStatement", pPIncludeStatement)
    }, firstToken, lastToken);
  }
  public IncludeBL(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IncludeBL(cloneProperties(),firstToken,lastToken);
  }
  public PPIncludeStatement getPPIncludeStatement() {
    return ((PropertyOne<PPIncludeStatement>)getProperty("pPIncludeStatement")).getValue();
  }
}
