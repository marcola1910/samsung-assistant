# 🛍️ Samsung Assistant — Shopping AI PoC

[![Java](https://img.shields.io/badge/Java-17+-red.svg)](https://openjdk.org/projects/jdk/17/)
[![Quarkus](https://img.shields.io/badge/Quarkus-3.12.3-blue.svg)](https://quarkus.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-orange.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

> Conversational **shopping assistant** for Samsung Brazil’s product catalog, built with **Quarkus + LangChain4j + Ollama + EasyRAG**.

---

## 🧩 Overview

This is a functional **Proof of Concept** of a shopping assistant capable of answering questions about Samsung products using **Retrieval-Augmented Generation (RAG)**.

It combines:

* ⚡ **Quarkus** — lightweight, reactive Java framework
* 🔗 **LangChain4j** — LLM orchestration in Java
* 🦙 **Ollama** — local LLM server (`gpt-oss:20b`)
* 📚 **EasyRAG** — contextual retrieval from JSON catalog

The assistant answers in **any language** — ask in Portuguese, English, or another language, and it will respond using catalog data.

---

## 📂 Project structure

```text
├── src
│   ├── main
│   │   ├── java/dev/ia
│   │   │   ├── SamsungAgentAssistant.java
│   │   │   └── SamsungAgentResource.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── rag/   ← context folder (JSON / RAG files)
│   └── test
├── pom.xml
├── README.md
└── mvnw*
```

---

## 🚀 Getting Started

### Prerequisites

* ☕ Java 17+
* 🔧 Maven 3.9+
* 🦙 Ollama installed (or running in Docker)
* 📂 Samsung catalog JSON placed in `src/main/resources/rag`

### Run locally

1. Clone the repository

```bash
git clone https://github.com/marcola1910/samsung-assistant.git
cd samsung-assistant
```

2. Start Ollama

```bash
ollama serve
ollama pull gpt-oss:20b
```

3. Run Quarkus in dev mode

```bash
./mvnw quarkus:dev
```

4. Query the REST endpoint

```bash
curl -X POST -H "Content-Type: text/plain" \
  -d "Qual o preço do Galaxy S24 Ultra?" \
  http://localhost:8080/samsung
```

5. (Optional) Build a production JAR

```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

---

## 💻 Implementation Details

* `SamsungAgentAssistant.java` → AI interface (`@RegisterAiService`)
* `SamsungAgentResource.java` → REST endpoint `/samsung`
* `application.properties` → config for Ollama + EasyRAG
* `rag/` → contains the JSON catalog for contextual retrieval

---

## 🧪 Examples

* Direct question:

```text
Qual o preço do produto SM-S928BZTKZTO?
```

→ Returns price, product link, and details.

* Open-ended question:

```text
Qual notebook da Samsung é melhor para jogos?
```

→ Compares available notebooks (RAM, CPU, GPU).

🌍 Multilingual: the model answers consistently in any language.

---

## 📚 About the RAG

The RAG is based on a structured JSON catalog with:

* **Categories** (e.g., `smartphones`, `galaxy-s`, `galaxy-book`)
* **Products** with `sku`, `title`, `url`, `price_cash`, `price_list`
* Rule: if `price_cash = null` → product is **out of stock**

---

## ⚠️ Limitations

* The `gpt-oss:20b` model is **heavy** → open-ended questions may take ~2 minutes on CPU.
* Catalog data may be outdated (prices change).
* Prototype doesn’t handle live inventory updates yet.

---

## 📈 Next Steps

* Optimize RAG (chunking, overlap, filters)
* Try smaller models (`llama3:8b`) for lower latency
* Add caching for frequent queries
* Deploy to GPU-backed server
* Create a friendly web/chat UI

---

## 📝 License & Contribution

Contributions are welcome!
Open issues or PRs to improve RAG, extend catalog data, or boost performance.

[MIT License](LICENSE)
