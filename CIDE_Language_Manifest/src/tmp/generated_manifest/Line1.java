package tmp.generated_manifest;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Line1 extends Line {
  public Line1(Header header, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Header>("header", header)
    }, firstToken, lastToken);
  }
  public Line1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Line1(cloneProperties(),firstToken,lastToken);
  }
  public Header getHeader() {
    return ((PropertyOne<Header>)getProperty("header")).getValue();
  }
}
