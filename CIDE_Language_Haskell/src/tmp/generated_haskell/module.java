package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class module extends GenASTNode implements ISourceFile {
  public module(moduleHeader moduleHeader, body body, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<moduleHeader>("moduleHeader", moduleHeader),
      new PropertyOne<body>("body", body)
    }, firstToken, lastToken);
  }
  public module(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new module(cloneProperties(),firstToken,lastToken);
  }
  public moduleHeader getModuleHeader() {
    return ((PropertyZeroOrOne<moduleHeader>)getProperty("moduleHeader")).getValue();
  }
  public body getBody() {
    return ((PropertyOne<body>)getProperty("body")).getValue();
  }
}
