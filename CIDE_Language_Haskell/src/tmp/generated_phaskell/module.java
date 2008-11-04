package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class module extends GenASTNode implements ISourceFile {
  public module(modid modid, exports exports, body body, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<modid>("modid", modid),
      new PropertyZeroOrOne<exports>("exports", exports),
      new PropertyOne<body>("body", body),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public module(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new module(cloneProperties(),firstToken,lastToken);
  }
  public modid getModid() {
    return ((PropertyOne<modid>)getProperty("modid")).getValue();
  }
  public exports getExports() {
    return ((PropertyZeroOrOne<exports>)getProperty("exports")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
