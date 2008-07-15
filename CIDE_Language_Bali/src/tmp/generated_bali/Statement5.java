package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement5 extends Statement {
  public Statement5(TokenManagerDeclarations tokenManagerDeclarations, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TokenManagerDeclarations>("tokenManagerDeclarations", tokenManagerDeclarations)
    }, firstToken, lastToken);
  }
  public Statement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement5(cloneProperties(),firstToken,lastToken);
  }
  public TokenManagerDeclarations getTokenManagerDeclarations() {
    return ((PropertyOne<TokenManagerDeclarations>)getProperty("tokenManagerDeclarations")).getValue();
  }
}
