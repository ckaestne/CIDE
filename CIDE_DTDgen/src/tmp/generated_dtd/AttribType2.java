package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttribType2 extends AttribType {
  public AttribType2(TokenizedType tokenizedType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TokenizedType>("tokenizedType", tokenizedType)
    }, firstToken, lastToken);
  }
  public AttribType2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttribType2(cloneProperties(),firstToken,lastToken);
  }
  public TokenizedType getTokenizedType() {
    return ((PropertyOne<TokenizedType>)getProperty("tokenizedType")).getValue();
  }
}
