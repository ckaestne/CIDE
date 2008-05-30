package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_span_Choice11 extends Content_span_Choice1 {
  public Content_span_Choice11(ASTStringNode pcdata, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pcdata", pcdata)
    }, firstToken, lastToken);
  }
  public Content_span_Choice11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_span_Choice11(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPcdata() {
    return ((PropertyOne<ASTStringNode>)getProperty("pcdata")).getValue();
  }
}
